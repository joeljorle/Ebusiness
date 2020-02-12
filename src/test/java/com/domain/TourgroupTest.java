package com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class TourgroupTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tourgroup.class);
        Tourgroup tourgroup1 = new Tourgroup();
        tourgroup1.setId(1L);
        Tourgroup tourgroup2 = new Tourgroup();
        tourgroup2.setId(tourgroup1.getId());
        assertThat(tourgroup1).isEqualTo(tourgroup2);
        tourgroup2.setId(2L);
        assertThat(tourgroup1).isNotEqualTo(tourgroup2);
        tourgroup1.setId(null);
        assertThat(tourgroup1).isNotEqualTo(tourgroup2);
    }
}
