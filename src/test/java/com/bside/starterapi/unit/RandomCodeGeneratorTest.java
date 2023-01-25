package com.bside.starterapi.unit;

import com.bside.starterapi.api.application.space.RandomCodeGenerator;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RandomCodeGeneratorTest {

    @DisplayName("랜덤 생성한 문자열이 숫자2개 알파벳8개를 포함한다.")
    @Test
    void code_generate() {
        String randomCode = RandomCodeGenerator.get();
        String[] strings = randomCode.split("");
        int numericCount = 0;
        int alphabetCount = 0;
        for (String string : strings) {
            if (StringUtils.isNumeric(string)) {
                numericCount++;
            }
            if (StringUtils.isAlpha(string)) {
                alphabetCount++;
            }
        }
        assertThat(numericCount).isEqualTo(2);
        assertThat(alphabetCount).isEqualTo(8);
    }
}
