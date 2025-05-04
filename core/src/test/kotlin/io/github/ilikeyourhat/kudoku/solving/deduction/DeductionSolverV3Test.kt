package io.github.ilikeyourhat.kudoku.solving.deduction

import io.github.ilikeyourhat.kudoku.solving.SolverContractTestTemplate
import io.github.ilikeyourhat.kudoku.solving.deduction.solver.DeductionSolverV3

class DeductionSolverV3Test : SolverContractTestTemplate<DeductionSolverV3>(
    solver = DeductionSolverV3()
)
