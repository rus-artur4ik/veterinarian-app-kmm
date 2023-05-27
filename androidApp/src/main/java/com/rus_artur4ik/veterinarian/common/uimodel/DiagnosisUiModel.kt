package com.rus_artur4ik.veterinarian.common.uimodel

import com.rus_artur4ik.veterinarian.domain.entity.DiagnosisEntity

data class DiagnosisUiModel(
    val diagnosis: DiagnosisEntity,
    val isExpanded: Boolean = false
)