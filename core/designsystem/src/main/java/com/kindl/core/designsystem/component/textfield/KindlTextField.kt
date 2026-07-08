package com.kindl.core.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kindl.core.designsystem.theme.KindlTheme

@Composable
fun KindlTextField(
    state: TextFieldState,
    placeholder: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    inputTransformation: InputTransformation? = null,
    outputTransformation: OutputTransformation? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
    shape: Shape = RoundedCornerShape(16.dp),
    containerColor: Color = KindlTheme.colors.slate600,
    textColor: Color = KindlTheme.colors.white,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val borderColor = when {
        isError -> KindlTheme.colors.red
        isFocused -> KindlTheme.colors.white
        else -> KindlTheme.colors.slate500
    }

    BasicTextField(
        state = state,
        enabled = enabled,
        readOnly = readOnly,
        modifier = modifier,
        inputTransformation = inputTransformation,
        outputTransformation = outputTransformation,
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
        lineLimits = lineLimits,
        textStyle = KindlTheme.typography.regular.body2.copy(color = textColor),
        cursorBrush = SolidColor(if (isError) KindlTheme.colors.red else KindlTheme.colors.white),
        interactionSource = interactionSource,
        decorator = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = containerColor,
                        shape = shape
                    )
                    .border(
                        width = 1.dp,
                        color = borderColor,
                        shape = shape
                    )
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (state.text.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = KindlTheme.typography.regular.body2,
                        color = KindlTheme.colors.slate400
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview
@Composable
private fun KindlTextFieldPreview() {
    KindlTheme {
        KindlTextField(
            state = rememberTextFieldState(initialText = ""),
            placeholder = "Placeholder",
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
