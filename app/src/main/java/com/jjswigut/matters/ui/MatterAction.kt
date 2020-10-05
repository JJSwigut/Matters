package com.jjswigut.matters.ui

import com.jjswigut.matters.database.Matter

sealed class MatterAction {
    data class MatterClicked(val position: Int, val matter: Matter) : MatterAction()
}