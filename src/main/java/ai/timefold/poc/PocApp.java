package ai.timefold.poc;

import ai.timefold.poc.domain.Employee;
import ai.timefold.poc.domain.Shift;
import ai.timefold.poc.timefold.move.SwapMove;
import ai.timefold.poc.timefold.selector.MoveStreamFactory;

public class PocApp {

    public static void main(String[] args) {



    }

//    public MoveStream requiredSkillSwapMove(MoveStreamFactory moveStreamFactory) {
//        return moveStreamFactory.forEach(Shift.class) // Short for forEachUnfiltered().filter(unpinnedPredicate)
//                .filter((Shift shift) -> shift.getRequiredSkill() != null)
//                .pick() // uniform() by default
//                .combineWithPick(Shift.class, // Short for moveStreamFactory.forEach(Shift.class)
//                        // uniform() pick by default
//                        // from list on this HashTable key
//                        equal(Shift::getRequiredSkill))
//                .move((Shift shift1, Shift shift2) -> new SwapMove<>(shift1, shift2));
//    }
//
//    public MoveStream nearbySwapMove(MoveStreamFactory moveStreamFactory) {
//        return moveStreamFactory.forEach(Visit.class)
//                .pick()
//                .combineWithPick(Visit.class,
//                        parabolic(),
//                        nearBy((Visit visit1, Visit visit2) -> visit1.getDistanceTo(visit2)), 40)
//                .move((Visit visit1, Visit visit2) -> new SwapMove<>(visit1, visit2));
//    }
//
//    public MoveStream shiftSequenceSwapMove(MoveStreamFactory moveStreamFactory) {
//        return moveStreamFactory.forEach(Shift.class)
//                .groupBy(Shift::getEmployee, toList())
//                // TODO sort by startDateTime
//                .pick(subList()) // TODO extract min startDateTime, max endDateTime
//                .combineWithPick(
//                        moveStreamFactory.forEach(Shift.class)
//                                .groupBy(Shift::getEmployee, toList())
//                        // TODO sort by startDateTime
//                        , from(s1.startDateTime <= s2.startDateTime),
//                        to(s1.endDateTime <= s2.endDateTime))
//                ).swapMove((Employee employee1, <Shift> shifts1, Employee employee2, List<Shift> shifts2)
//                    -> new SwapListMove(employee1, shifts1, employee2, shifts2))
//    }

    // TODO subList etc


    // untilAtLeastEqual(sum(Shift::getDuration), sum(Shift::getDuration))
}
