package com.example.sicreditest.commom

class validator {
    fun execute(texto : String): ValidationResult{
        if (texto.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "Preencha o campo"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)