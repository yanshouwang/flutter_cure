package dev.yanshouwang.cure.security

interface IAuthority {
    fun check(): AuthorityState
    fun request(requestCode: Int)
}