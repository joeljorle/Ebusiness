package com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class ActusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Actus.class);
        Actus actus1 = new Actus();
        actus1.setId(1L);
        Actus actus2 = new Actus();
        actus2.setId(actus1.getId());
        assertThat(actus1).isEqualTo(actus2);
        actus2.setId(2L);
        assertThat(actus1).isNotEqualTo(actus2);
        actus1.setId(null);
        assertThat(actus1).isNotEqualTo(actus2);
    }
}
