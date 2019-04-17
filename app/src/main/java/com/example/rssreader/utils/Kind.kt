package com.example.rssreader.utils

enum class Kind(val value: Int) {
    KIND_VN_EXPRESS(0), KIND_24H(1);

    companion object {
        fun from(value: Int): Kind {
            return when (value) {
                0 -> KIND_VN_EXPRESS
                1 -> KIND_24H
                else -> KIND_VN_EXPRESS
            }
        }
    }
}
