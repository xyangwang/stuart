package io.stuart.utils;

import java.util.ArrayList;
import java.util.List;

import io.stuart.consts.TopicConst;
import io.stuart.entities.cache.MqttTrie;

public class TopicUtil {

    public static boolean checkSubscribeTopic(String topic) {
        if (!validateTopic(topic)) {
            return false;
        }

        // get '#' index of topic string
        int index = topic.indexOf(TopicConst.POUND);

        // check '#' is the last character of topic string
        if (index >= 0 && (index != topic.length() - 1)) {
            return false;
        }

        // get topic words
        String[] words = topic.split(TopicConst.SLASH);

        // check every word, if word contains '#' or '+', the word should not
        // contains other characters
        for (String word : words) {
            if (word.length() > 1 && (word.contains(TopicConst.POUND) || word.contains(TopicConst.PLUS))) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkPublishTopic(String topic) {
        if (!validateTopic(topic) || topic.contains(TopicConst.POUND) || topic.contains(TopicConst.PLUS)) {
            return false;
        }

        return true;
    }

    public static boolean validateTopic(String topic) {
        if (topic == null || topic.length() == 0) {
            return false;
        }

        return true;
    }

    public static boolean isWildcard(String topic) {
        if (topic != null && (topic.contains(TopicConst.POUND) || topic.contains(TopicConst.PLUS))) {
            return true;
        } else {
            return false;
        }
    }

    public static String[] words(String topic) {
        String[] split = topic.split(TopicConst.SLASH);
        int length = split.length;

        String[] result = null;

        if (topic.endsWith(TopicConst.SLASH)) {
            result = new String[length + 1];
            System.arraycopy(split, 0, result, 0, length);
            result[length] = "";
        } else {
            result = split;
        }

        return result;
    }

    public static List<MqttTrie> topic2Trie(String topic) {
        if (!validateTopic(topic)) {
            return null;
        }

        List<MqttTrie> nodes = new ArrayList<>();

        String[] words = words(topic);
        String word = null;
        StringBuilder parent = new StringBuilder();
        MqttTrie node = null;

        for (int i = 0; i < words.length; ++i) {
            word = words[i];

            node = new MqttTrie();
            node.setWord(word);
            node.setCount(1);

            if (i == 0) {
                node.setParent(null);
                parent.append(word);
            } else {
                node.setParent(parent.toString());
                parent.append(TopicConst.SLASH).append(word);
            }

            node.setSelf((node.getParent() == null ? "" : node.getParent() + TopicConst.SLASH) + word);

            nodes.add(node);
        }

        return nodes;
    }

}
