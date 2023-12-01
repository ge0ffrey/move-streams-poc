package ai.timefold.poc.timefold.move;

import java.util.Iterator;
import java.util.function.Function;

import ai.timefold.poc.timefold.selector.BiSelector;

public class Moves {

    public static <A> Function<BiSelector<A, A>, Iterator<SwapMove<A>>> swap() {
        return selector -> new Iterator<>() {
            int count = 0;

            @Override
            public boolean hasNext() {
                return count++ < 10; // TODO HACK
            }

            @Override
            public SwapMove<A> next() {
                A a1 = selector.getSelectorA().pick();
                A a2 = selector.getSelectorB().pick();
                return new SwapMove<>(a1, a2);
            }
        };
    }

    public static <Value_, Entity_>
            Function<BiSelector<Pillar<Value_, Entity_>, Pillar<Value_, Entity_>>, Iterator<PillarSwapMove<Value_, Entity_>>>
            swapPillar() {
        // TODO caching: Map<Employee, List<Shift>> employeeToShiftMap = shiftList.stream().collect(Collectors.groupingBy(Shift::getEmployee));
        return selector -> new Iterator<>() {
            int count = 0;

            @Override
            public boolean hasNext() {
                return count++ < 10; // TODO HACK
            }

            @Override
            public PillarSwapMove<Value_, Entity_> next() {
                Pillar<Value_, Entity_> pillar1 = selector.getSelectorA().pick();
                Pillar<Value_, Entity_> pillar2 = selector.getSelectorB().pick();
                return new PillarSwapMove<>(pillar1, pillar2);
            }
        };
    }

}
