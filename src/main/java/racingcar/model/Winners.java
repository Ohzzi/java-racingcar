package racingcar.model;

import java.util.List;
import java.util.stream.Collectors;

public class Winners {
    private static final int MIN_POSITION = 0;

    private final List<String> names;

    public Winners(List<Car> cars) {
        int maxPosition = getMaxPosition(cars);
        names = cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    public List<String> getNames() {
        return names;
    }

    private int getMaxPosition(List<Car> cars) {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(MIN_POSITION);
    }
}
