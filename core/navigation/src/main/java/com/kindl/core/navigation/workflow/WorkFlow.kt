package com.kindl.core.navigation.workflow

/**
 * ViewModel 주입용 외부 노출용 인터페이스
 * @param S: Step
 * @param D: Data
 * */
interface FlowGraph<S, D> {
    fun getNextStep(current: S, data: D): S?
    fun getPreviousStep(current: S): S?
}

/**
 * DSL 진입점 (Entry Point)
 * */
fun <S, D> buildFlowGraph(init: FlowGraphBuilder<S, D>.() -> Unit): FlowGraph<S, D> =
    FlowGraphBuilder<S, D>().apply(init).build()

/**
 * 완성 개별 부품
 * */
internal class StepRule<S, D>(
    val nextRule: ((D) -> S?)?,
    val previousRule: (() -> S?)?
)


/**
 * 개별 스텝 작업자
 * */
class StepRuleBuilder<S, D> {
    private var nextRule: ((D) -> S?)? = null
    private var previousRule: (() -> S?)? = null

    fun next(rule: (D) -> S?) { nextRule = rule }
    fun previous(rule: () -> S?) { previousRule = rule }

    internal fun build() = StepRule(nextRule, previousRule)
}

/**
 * 전체 조립 라인
 * */
class FlowGraphBuilder<S, D> {
    private val rules = mutableMapOf<S, StepRuleBuilder<S, D>>()

    fun step(step: S, init: StepRuleBuilder<S, D>.() -> Unit) {
        rules[step] = StepRuleBuilder<S, D>().apply(init)
    }

    internal fun build(): FlowGraph<S, D> =
        FlowGraphImpl(rules.mapValues { it.value.build() })
}

/**
 * 완성된 전체 흐름 구현체
 * */
private class FlowGraphImpl<S, D>(
    private val rules: Map<S, StepRule<S, D>>
) : FlowGraph<S, D> {
    override fun getNextStep(current: S, data: D): S? =
        rules[current]?.nextRule?.invoke(data)

    override fun getPreviousStep(current: S): S? =
        rules[current]?.previousRule?.invoke()
}
