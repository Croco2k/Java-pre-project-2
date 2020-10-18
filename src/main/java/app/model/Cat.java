package app.model;

import org.springframework.stereotype.Component;

@Component
public class Cat implements Animal {
    @Override
    public String toString() {
        return "Im a Cat";
    }
}
