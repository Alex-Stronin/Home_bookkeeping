package project.dto;

import java.math.BigDecimal;

public record UserDto(String name, String login, String password, BigDecimal startCapital) {
}
