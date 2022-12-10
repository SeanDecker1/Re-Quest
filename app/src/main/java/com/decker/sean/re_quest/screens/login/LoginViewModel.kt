package com.decker.sean.re_quest.screens.login

import androidx.compose.runtime.mutableStateOf
import com.decker.sean.re_quest.LOGIN_SCREEN

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : ReQuestViewModel(logService) {

    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    } // Ends onEmailChange

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    } // Ends onPasswordChange

    fun onSignInClick(openAndPopup: (String, String) -> Unit) {

        if (!email.isValidEmail()) {
            SnackbarManager.showMessage("Invalid email")
            return
        } // Ends if

        if (password.isBlank()) {
            SnackbarManager.showMessage("Password cannot be empty")
            return
        } // Ends if

        launchCatching {
            accountService.authenticate(email, password)
            openAndPopUp(QUEST_LIST_SCREEN, LOGIN_SCREEN)
        } // Ends launchCatching

    } // Ends onSignInClick

    //
    // Don't need this
    //
    fun onForgotPasswordClick() {

        if (!email.isValidEmail()) {
            SnackbarManager.showMessage("Invalid email")
            return
        } // Ends if

        launchCatching {
            accountService.sendRecoveryEmail(email)
            SnackbardManager.showMessage("Check your email for the recovery link")
        } // Ends launchCatching

    } // Ends onForgotPasswordClick

} // Ends class