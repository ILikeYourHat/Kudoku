package io.github.ilikeyourhat.kudoku.solving.deduction

import io.github.ilikeyourhat.kudoku.solving.SolverContractTestTemplate
import io.github.ilikeyourhat.kudoku.solving.deduction.solver.DeductionSolverV2

class DeductionSolverV2Test : SolverContractTestTemplate<DeductionSolverV2>(
    solver = DeductionSolverV2()
)
