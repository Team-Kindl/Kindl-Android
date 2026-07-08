package com.kindl.onboarding.di

import com.kindl.core.navigation.workflow.FlowGraph
import com.kindl.core.navigation.workflow.buildFlowGraph
import com.kindl.onboarding.model.OnboardingStep
import com.kindl.onboarding.model.OnboardingStepUiModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object OnboardingFlowModule {

    @Provides
    fun provideOnboardingFlowGraph(): FlowGraph<OnboardingStep, OnboardingStepUiModel> =
        buildFlowGraph {
            step(OnboardingStep.MAIN) {
                next { OnboardingStep.NAME }
            }

            step(OnboardingStep.NAME) {
                next {
                    if (it.nickname.isNotBlank()) OnboardingStep.FORBIDDEN
                    else null
                }
                previous { OnboardingStep.MAIN }
            }

            step(OnboardingStep.FORBIDDEN) {
                next { data ->
                    if (data.forbiddenApps.isNotEmpty()) OnboardingStep.POINT
                    else null
                }
                previous { OnboardingStep.NAME }
            }

            step(OnboardingStep.POINT) {
                next { OnboardingStep.COMPLETE }
                previous { OnboardingStep.FORBIDDEN }
            }
        }
}
