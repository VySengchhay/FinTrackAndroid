package com.androidapp.fintrackandroid.feature.auth.presentation.login

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.androidapp.fintrackandroid.core.ui.component.FinTrackButton
import com.androidapp.fintrackandroid.core.ui.component.FinTrackTextField
import com.androidapp.fintrackandroid.core.ui.theme.FinTrackSpacing
import com.androidapp.fintrackandroid.core.ui.theme.FinTrackTheme

@Composable
fun LoginScreen(
    onLogin: (
        email: String,
        password: String
    ) -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    errorMessage: String? = null
) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var isPasswordVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var emailError by rememberSaveable {
        mutableStateOf<String?>(null)
    }
    var passwordError by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    fun submitLogin() {
        val newEmailError = validateEmail(email)
        val newPasswordError = validatePassword(password)

        emailError = newEmailError
        passwordError = newPasswordError

        if (
            newEmailError == null &&
            newPasswordError == null &&
            !isLoading
        ) {
            onLogin(
                email.trim(),
                password
            )
        }
    }

    LoginContent(
        state = LoginUiState(
            email = email,
            password = password,
            isPasswordVisible = isPasswordVisible,
            emailError = emailError,
            passwordError = passwordError,
            isLoading = isLoading,
            errorMessage = errorMessage
        ),
        onEmailChange = { newEmail ->
            email = newEmail

            if (emailError != null) {
                emailError = validateEmail(newEmail)
            }
        },
        onPasswordChange = { newPassword ->
            password = newPassword

            if (passwordError != null) {
                passwordError = validatePassword(newPassword)
            }
        },
        onPasswordVisibilityToggle = {
            isPasswordVisible = !isPasswordVisible
        },
        onLoginClick = ::submitLogin,
        modifier = modifier
    )
}

@Composable
private fun LoginContent(
    state: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityToggle: () -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(
                horizontal = FinTrackSpacing.screenHorizontal,
                vertical = FinTrackSpacing.screenVertical
            ),
        verticalArrangement = Arrangement.spacedBy(
            FinTrackSpacing.medium,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome back",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Sign in to continue managing your finances.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        FinTrackTextField(
            value = state.email,
            onValueChange = onEmailChange,
            label = "Email",
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading,
            isError = state.emailError != null,
            supportingText = state.emailError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        FinTrackTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            label = "Password",
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading,
            isError = state.passwordError != null,
            supportingText = state.passwordError,
            visualTransformation = if (
                state.isPasswordVisible
            ) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onLoginClick()
                }
            ),
            trailingIcon = {
                IconButton(
                    onClick = onPasswordVisibilityToggle,
                    enabled = !state.isLoading
                ) {
                    Icon(
                        imageVector = if (
                            state.isPasswordVisible
                        ) {
                            Icons.Outlined.VisibilityOff
                        } else {
                            Icons.Outlined.Visibility
                        },
                        contentDescription = if (
                            state.isPasswordVisible
                        ) {
                            "Hide password"
                        } else {
                            "Show password"
                        }
                    )
                }
            }
        )

        state.errorMessage?.let { message ->
            Text(
                text = message,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error
            )
        }

        FinTrackButton(
            text = "Log in",
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth(),
            isLoading = state.isLoading,
            loadingText = "Signing in"
        )
    }
}

private data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

private fun validateEmail(
    email: String
): String? {
    val normalizedEmail = email.trim()

    return when {
        normalizedEmail.isEmpty() ->
            "Email is required"

        !Patterns.EMAIL_ADDRESS
            .matcher(normalizedEmail)
            .matches() ->
            "Enter a valid email address"

        else -> null
    }
}

private fun validatePassword(
    password: String
): String? {
    return when {
        password.isEmpty() ->
            "Password is required"

        password.length < MINIMUM_PASSWORD_LENGTH ->
            "Password must be at least $MINIMUM_PASSWORD_LENGTH characters"

        else -> null
    }
}

private const val MINIMUM_PASSWORD_LENGTH = 8

@Preview(
    name = "Login - Normal",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun LoginNormalPreview() {
    FinTrackTheme {
        LoginContent(
            state = LoginUiState(),
            onEmailChange = {},
            onPasswordChange = {},
            onPasswordVisibilityToggle = {},
            onLoginClick = {}
        )
    }
}

@Preview(
    name = "Login - Error",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun LoginErrorPreview() {
    FinTrackTheme {
        LoginContent(
            state = LoginUiState(
                email = "invalid-email",
                password = "123",
                emailError = "Enter a valid email address",
                passwordError =
                    "Password must be at least 8 characters",
                errorMessage =
                    "The email or password is incorrect."
            ),
            onEmailChange = {},
            onPasswordChange = {},
            onPasswordVisibilityToggle = {},
            onLoginClick = {}
        )
    }
}

@Preview(
    name = "Login - Loading",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun LoginLoadingPreview() {
    FinTrackTheme {
        LoginContent(
            state = LoginUiState(
                email = "user@example.com",
                password = "password",
                isLoading = true
            ),
            onEmailChange = {},
            onPasswordChange = {},
            onPasswordVisibilityToggle = {},
            onLoginClick = {}
        )
    }
}