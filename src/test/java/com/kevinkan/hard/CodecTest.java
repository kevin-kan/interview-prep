package com.kevinkan.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.kevinkan.utility.TreeNode;

/**
 * Unit tests for Codec class.
 */
public class CodecTest {

    private static Stream<Arguments> levelOrderTestData() {
        return Stream.of(
            Arguments.of(TreeNode.fromLevelOrder(new ArrayList<>(Arrays.asList(1, 2, 3, null, null, 4, 5))), "1,2,3,null,null,4,5"),
            Arguments.of(null, "")
        );
    }

    private static Stream<Arguments> preOrderTestData() {
        return Stream.of(
            Arguments.of(TreeNode.fromPreOrder(new ArrayList<>(Arrays.asList(1, 2, null, null, 3, 4, 5))), "1,2,null,null,3,4,5"),
            Arguments.of(null, "")
        );
    }

    @ParameterizedTest
    @MethodSource("levelOrderTestData")
    public void testLevelOrderSerializeAndDeserialize(TreeNode deserialized, String serialized) {
        String serializedTree = Codec.serialize_levelOrder(deserialized);
        assertEquals(serialized, serializedTree);
        TreeNode deserializedTree = Codec.deserialize_levelOrder(serialized);
        assertEquals(deserialized, deserializedTree);
    }

    @ParameterizedTest
    @MethodSource("preOrderTestData")
    public void testPreOrderSerializeAndDeserialize(TreeNode deserialized, String serialized) {
        String serializedTree = Codec.serialize_preOrder(deserialized);
        assertEquals(serialized, serializedTree);
        TreeNode deserializedTree = Codec.deserialize_preOrder(serialized);
        assertEquals(deserialized, deserializedTree);
    }
}
