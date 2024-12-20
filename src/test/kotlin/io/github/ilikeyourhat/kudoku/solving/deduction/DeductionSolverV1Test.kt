package io.github.ilikeyourhat.kudoku.solving.deduction

import io.github.ilikeyourhat.kudoku.solving.SolverContractTestTemplate
import io.github.ilikeyourhat.kudoku.solving.deduction.solver.DeductionSolverV1

class DeductionSolverV1Test : SolverContractTestTemplate<DeductionSolverV1>(
    solver = DeductionSolverV1()
)
