package com.example.githubtechincaltest.util

class TrieNode {
    val children: MutableMap<Char, TrieNode> = mutableMapOf()
    var isEndOfWord: Boolean = false
}