package racingcar.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import racingcar.message.ErrorMessages;
import racingcar.util.RandomNumberGenerator;

public class Cars {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String DELIMITER = ",";

    private final List<Car> cars = new ArrayList<>();

    public Cars(String carNames) {
        String[] carNameArray = reduceBlank(carNames).split(DELIMITER);
        validateDuplicatedName(carNameArray);
        for (String carName : carNameArray) {
            cars.add(new Car(carName));
        }
    }

    public void moveAll(RandomNumberGenerator random) {
        for (Car car : cars) {
            car.goOrStop(random.generate());
        }
    }

    public Winners getWinners() {
        return new Winners(cars);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Car car : cars) {
            sb.append(car.toString())
                    .append(NEW_LINE);
        }
        return sb.toString();
    }

    private void validateDuplicatedName(String[] carNames) {
        long distinctSize = Arrays.stream(carNames)
                .distinct().count();
        if (distinctSize != carNames.length) {
            throw new IllegalArgumentException(ErrorMessages.DUPLICATED_NAME);
        }
    }

    private String reduceBlank(String string) {
        return string.replaceAll(" ", "");
    }
}
