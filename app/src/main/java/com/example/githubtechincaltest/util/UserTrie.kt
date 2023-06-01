package com.example.githubtechincaltest.util

class UserTrie {
    private val root: TrieNode = TrieNode()

    fun insertUser(username: String) {
        var currentNode = root

        for (char in username) {
            val childNode = currentNode.children[char] ?: TrieNode()
            currentNode.children[char] = childNode
            currentNode = childNode
        }

        currentNode.isEndOfWord = true
    }

    //Returns list of possible usernames
    fun searchUsers(query: String): List<String> {
        val results: MutableList<String> = mutableListOf()
        var currentNode = root

        for (char in query) {
            currentNode = currentNode.children[char] ?: return results
        }

        collectWords(currentNode, query, results)

        return results
    }

    private fun collectWords(node: TrieNode, prefix: String, results: MutableList<String>) {
        if (node.isEndOfWord) {
            results.add(prefix)
        }

        for ((char, childNode) in node.children) {
            collectWords(childNode, prefix + char, results)
        }
    }
}