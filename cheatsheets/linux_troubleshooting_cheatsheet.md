# Linux Troubleshooting Cheat Sheet

## 🎯 General Troubleshooting Mindset

**The 4-Step Process:**
1. **Identify the problem**: What's broken? What changed?
2. **Gather information**: Logs, metrics, system state
3. **Form hypothesis**: What could cause this?
4. **Test & validate**: Try fixes, verify results

**Key principle**: Work from **symptoms → root cause**, not the other way around

---

## 📁 File System & Navigation

### Basic Navigation
```bash
pwd                    # Print working directory
ls -la                 # List all files (including hidden) with details
cd /path/to/dir        # Change directory
cd ~                   # Go to home directory
cd -                   # Go to previous directory
```

### Finding Files
```bash
find /path -name "*.log"              # Find files by name
find /var/log -mtime -7               # Modified in last 7 days
find / -size +100M                    # Files larger than 100MB
locate filename                       # Fast search (uses database)
which python                          # Find binary location
whereis python                        # Find binary, source, man pages
```

### File Operations
```bash
cat file.txt                          # Display file content
less file.txt                         # View file (paginated, searchable)
head -n 20 file.txt                   # First 20 lines
tail -n 20 file.txt                   # Last 20 lines
tail -f /var/log/app.log              # Follow log in real-time (CRITICAL for debugging)

grep "error" file.txt                 # Search for pattern
grep -r "error" /var/log/             # Recursive search
grep -i "error" file.txt              # Case-insensitive
grep -A 5 "error" file.txt            # Show 5 lines after match
grep -B 5 "error" file.txt            # Show 5 lines before match
grep -C 5 "error" file.txt            # Show 5 lines before & after
```

### Permissions
```bash
ls -l file.txt                        # Check permissions
# Output: -rw-r--r-- 1 user group size date file
#         ↑        ↑ ↑    ↑
#         type     owner group
#         rwx (read/write/execute)

chmod 755 script.sh                   # rwxr-xr-x (owner: all, others: read+execute)
chmod +x script.sh                    # Add execute permission
chmod -R 644 /path/to/dir             # Recursive permission change

chown user:group file.txt             # Change owner and group
chown -R user:group /path/to/dir      # Recursive ownership change

# Common permissions:
# 644: rw-r--r-- (files)
# 755: rwxr-xr-x (directories, executables)
# 600: rw------- (private files)
# 777: rwxrwxrwx (everyone can do everything - AVOID!)
```

---

## 🔧 Process Management

### Viewing Processes
```bash
ps aux                               # All processes (detailed)
ps aux | grep python                 # Find specific process
ps -ef                               # All processes (different format)
ps -u username                       # Processes by user

top                                  # Interactive process viewer (press 'q' to quit)
# In top:
# - Press 'P' to sort by CPU
# - Press 'M' to sort by memory
# - Press 'k' then enter PID to kill process

htop                                 # Better version of top (if installed)
```

### Managing Processes
```bash
kill PID                             # Graceful termination (SIGTERM)
kill -9 PID                          # Force kill (SIGKILL)
killall process_name                 # Kill all processes by name
pkill -f "pattern"                   # Kill processes matching pattern

# Background & foreground
command &                            # Run in background
jobs                                 # List background jobs
fg %1                                # Bring job 1 to foreground
bg %1                                # Resume job 1 in background
Ctrl+Z                               # Suspend current process
Ctrl+C                               # Kill current process

# Process priority
nice -n 10 command                   # Start with lower priority (+19 = lowest, -20 = highest)
renice -n 5 -p PID                   # Change priority of running process
```

---

## 🌐 Networking

### Basic Connectivity
```bash
ping google.com                      # Test connectivity (Ctrl+C to stop)
ping -c 4 google.com                 # Send 4 packets and stop

curl https://api.example.com         # Fetch URL
curl -I https://example.com          # Get headers only
curl -X POST -d "data" URL           # POST request
wget https://example.com/file.zip    # Download file
```

### Network Information
```bash
ifconfig                             # Network interface info (older)
ip addr show                         # Network interface info (modern)
ip route                             # Routing table

netstat -tuln                        # Listening ports (TCP/UDP)
# -t: TCP, -u: UDP, -l: listening, -n: numeric (don't resolve names)

netstat -anp | grep :80              # What's using port 80?
ss -tuln                             # Modern replacement for netstat

lsof -i :8080                        # What process is using port 8080?
lsof -i TCP                          # All TCP connections
```

### DNS & Routing
```bash
nslookup google.com                  # DNS lookup
host google.com                      # DNS lookup (simpler)
dig google.com                       # DNS lookup (detailed)

traceroute google.com                # Trace route to destination
tracepath google.com                 # Alternative to traceroute

# Check if port is open
telnet example.com 80                # Test TCP connection
nc -zv example.com 80                # Test port (nc = netcat)
```

---

## 💾 Disk & Storage

### Disk Usage
```bash
df -h                                # Disk space (human-readable)
df -h /                              # Disk space for root partition
df -i                                # Inode usage (important!)

du -sh /path/to/dir                  # Total size of directory
du -sh *                             # Size of all items in current dir
du -h --max-depth=1 /var             # Size of subdirectories (1 level deep)
du -ah /var/log | sort -rh | head -n 20  # Top 20 largest files/dirs
```

### Disk I/O
```bash
iostat                               # CPU and I/O statistics
iostat -x 1                          # Extended stats, refresh every 1 sec
iotop                                # Interactive I/O monitor (like top for disk)

lsblk                                # List block devices
mount                                # Show mounted filesystems
mount /dev/sdb1 /mnt                 # Mount device
umount /mnt                          # Unmount
```

### Open Files
```bash
lsof                                 # List all open files
lsof -u username                     # Open files by user
lsof -p PID                          # Open files by process
lsof /path/to/file                   # What process has this file open?
lsof +D /path/to/dir                 # Open files in directory
```

---

## 📊 System Performance

### CPU
```bash
uptime                               # System uptime + load average
# Load average: 1-min, 5-min, 15-min averages
# Rule of thumb: < number of CPU cores = healthy

lscpu                                # CPU information
cat /proc/cpuinfo                    # Detailed CPU info
nproc                                # Number of CPU cores

mpstat 1                             # CPU stats per core (refresh every 1 sec)
```

### Memory
```bash
free -h                              # Memory usage (human-readable)
# Output:
#               total   used   free   shared  buff/cache  available
# Mem:          16Gi    4Gi    8Gi    100Mi   4Gi         11Gi
# Swap:         2Gi     0      2Gi

vmstat 1                             # Virtual memory stats (refresh every 1 sec)
cat /proc/meminfo                    # Detailed memory info
```

### System Resources
```bash
dmesg                                # Kernel messages (hardware errors, etc.)
dmesg | grep -i error                # Search for errors
dmesg -T                             # Human-readable timestamps

uname -a                             # System information
hostnamectl                          # Hostname and OS info
```

---

## 📝 Logs

### Common Log Locations
```bash
/var/log/syslog                      # General system log (Debian/Ubuntu)
/var/log/messages                    # General system log (RHEL/CentOS)
/var/log/auth.log                    # Authentication logs
/var/log/kern.log                    # Kernel logs
/var/log/dmesg                       # Boot messages
/var/log/apache2/                    # Apache logs
/var/log/nginx/                      # Nginx logs
/var/log/mysql/                      # MySQL logs
```

### Reading Logs
```bash
tail -f /var/log/syslog                     # Follow log in real-time
tail -n 100 /var/log/syslog                 # Last 100 lines

grep "error" /var/log/syslog                # Search for errors
grep -i "failed" /var/log/auth.log          # Case-insensitive search

# View logs by time
journalctl                                   # All systemd logs
journalctl -u nginx                          # Logs for nginx service
journalctl -f                                # Follow logs
journalctl --since "2024-02-08"              # Logs since date
journalctl --since "1 hour ago"              # Last hour
journalctl -p err                            # Only errors
journalctl -xe                               # Last logs with explanation
```

---

## 🚀 Service Management (systemd)

```bash
systemctl status nginx                       # Check service status
systemctl start nginx                        # Start service
systemctl stop nginx                         # Stop service
systemctl restart nginx                      # Restart service
systemctl reload nginx                       # Reload config (no downtime)

systemctl enable nginx                       # Start on boot
systemctl disable nginx                      # Don't start on boot

systemctl list-units --type=service          # List all services
systemctl list-units --failed                # Failed services
```

---

## 🔍 Troubleshooting Scenarios

### 🚨 Scenario 1: Service is Down

**Goal**: Get the service back up and understand why it failed

```bash
# 1. Check if service is running
systemctl status myapp
# Look for: "active (running)" or "failed"

# 2. If failed, check recent logs
journalctl -u myapp -n 50
# Look for: error messages, stack traces

# 3. Check if port is already in use
lsof -i :8080
netstat -tuln | grep 8080

# 4. Try to start the service
systemctl start myapp

# 5. If still failing, check config
cat /etc/myapp/config.conf
# Look for: syntax errors, wrong paths

# 6. Check permissions
ls -la /var/log/myapp/
ls -la /etc/myapp/

# 7. Try running manually (if possible)
/usr/bin/myapp --config /etc/myapp/config.conf
# See direct error output
```

---

### 🐌 Scenario 2: Server is Slow

**Goal**: Identify the bottleneck (CPU, memory, disk, network)

```bash
# 1. Check overall load
uptime
# Load > CPU cores? Problem!

# 2. Check CPU usage
top
# Press 'P' to sort by CPU
# Look for: processes using >50% CPU

# 3. Check memory
free -h
# Is "available" memory low?
# Is swap being used heavily?

# 4. Check disk I/O
iostat -x 1
# Look for: high %util (>80%) or long await times

# 5. Find what's eating disk I/O
iotop
# Look for: processes with high DISK READ/WRITE

# 6. Check disk space
df -h
# Any partition >90% full?

# 7. Check for runaway processes
ps aux | sort -k 3 -r | head -n 10      # Top 10 CPU users
ps aux | sort -k 4 -r | head -n 10      # Top 10 memory users

# 8. Check network
netstat -i                               # Network interface stats
iftop                                    # Real-time network usage (if installed)
```

---

### 🌐 Scenario 3: Network/Connectivity Issue

**Goal**: Determine if problem is local, network, or remote

```bash
# 1. Check if interface is up
ip addr show
# Look for: "UP" status, IP address assigned

# 2. Test local connectivity
ping 127.0.0.1                           # Localhost (should always work)

# 3. Test gateway connectivity
ip route                                 # Find default gateway
ping 192.168.1.1                         # Ping gateway

# 4. Test DNS
ping google.com                          # Does DNS resolve?
nslookup google.com                      # Check DNS resolution
cat /etc/resolv.conf                     # Check DNS servers

# 5. Test external connectivity
ping 8.8.8.8                             # Google DNS (bypass DNS)

# 6. Trace route
traceroute google.com                    # Where does it fail?

# 7. Check if service is listening
netstat -tuln | grep :80
lsof -i :80

# 8. Test port connectivity
telnet example.com 80
curl -v http://example.com

# 9. Check firewall
sudo iptables -L                         # Firewall rules
sudo ufw status                          # UFW firewall (Ubuntu)
```

---

### 💥 Scenario 4: High Memory Usage / Memory Leak

**Goal**: Find what's consuming memory and why

```bash
# 1. Check memory status
free -h
# Is "available" low? Is swap being used?

# 2. Find memory hogs
ps aux | sort -k 4 -r | head -n 10       # Top 10 memory users

# 3. Check for memory leak
top
# Watch "RES" column for a process - is it growing over time?

# 4. Get detailed process memory
cat /proc/PID/status | grep -i mem
pmap -x PID                              # Memory map of process

# 5. Check if it's cache (good) or actual usage (bad)
free -h
# "buff/cache" is OK - kernel can reclaim it
# Low "available" is bad

# 6. Check OOM (Out of Memory) killer logs
dmesg | grep -i "killed process"
journalctl -k | grep -i "out of memory"

# 7. If needed, clear cache (won't harm running processes)
sudo sync                                # Flush file system buffers
sudo echo 3 > /proc/sys/vm/drop_caches   # Clear cache (use carefully!)
```

---

### 🔥 Scenario 5: High CPU Usage

**Goal**: Find what's consuming CPU and optimize or kill it

```bash
# 1. Check CPU load
uptime
top
# Press '1' to see per-core usage

# 2. Find CPU hogs
top                                      # Press 'P' to sort by CPU
ps aux | sort -k 3 -r | head -n 10       # Top 10 CPU users

# 3. Check what the process is doing
strace -p PID                            # Trace system calls (advanced)
lsof -p PID                              # What files is it accessing?

# 4. Check for infinite loops or runaway scripts
ps aux | grep python                     # Find your scripts
cat /proc/PID/cmdline                    # See full command

# 5. Nice down or kill the process
renice -n 10 -p PID                      # Lower priority
kill PID                                 # Graceful kill
kill -9 PID                              # Force kill (last resort)
```

---

## 🎯 Quick Reference: When to Use What

| Symptom | First Command | Follow-up |
|---------|---------------|-----------|
| Service won't start | `systemctl status service` | `journalctl -u service -n 50` |
| Port already in use | `lsof -i :PORT` | `kill PID` |
| Server slow | `top` | `iostat`, `free -h` |
| Disk full | `df -h` | `du -sh *`, find large files |
| High CPU | `top` | `ps aux`, `strace -p PID` |
| High memory | `free -h` | `ps aux`, check for leaks |
| Network issue | `ping gateway` | `traceroute`, `netstat -tuln` |
| App crashing | `journalctl -u app` | Check logs, permissions |
| Permission denied | `ls -la file` | `chmod`, `chown` |

---

## 💡 Pro Tips for Interviews

1. **Talk through your process**: Explain *why* you're running each command
2. **Start broad, then narrow**: System → Service → Process → File
3. **Check logs first**: 80% of issues are explained in logs
4. **Don't panic**: Take it step-by-step, even if you don't know the exact command
5. **Common sense checks**: Is the service running? Is the config correct? Are permissions set?
6. **Use `man` or `--help`**: It's OK to check syntax (e.g., `man grep`)
7. **Simulate on your own machine**: Practice these commands before the interview!

---

## 🔧 Bonus: One-Liners You Should Know

```bash
# Find and kill process using port 8080
lsof -ti :8080 | xargs kill -9

# Find top 10 largest files
find / -type f -exec du -h {} + | sort -rh | head -n 10

# Monitor log for errors in real-time
tail -f /var/log/syslog | grep -i error

# Check which services failed to start
systemctl list-units --failed

# Clear RAM cache (safe)
sync && echo 3 > /proc/sys/vm/drop_caches

# Find files modified in last 24 hours
find /var/log -mtime -1

# Check open connections to port 80
netstat -an | grep :80 | wc -l

# Disk usage of current directory, sorted
du -h --max-depth=1 | sort -rh
```

---

**You've got this!** Remember: In interviews, **clear thinking > memorization**. Talk through your debugging process, ask clarifying questions, and stay calm. Good luck! 🚀
