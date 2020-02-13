package com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class RatingsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ratings.class);
        Ratings ratings1 = new Ratings();
        ratings1.setId(1L);
        Ratings ratings2 = new Ratings();
        ratings2.setId(ratings1.getId());
        assertThat(ratings1).isEqualTo(ratings2);
        ratings2.setId(2L);
        assertThat(ratings1).isNotEqualTo(ratings2);
        ratings1.setId(null);
        assertThat(ratings1).isNotEqualTo(ratings2);
    }
}
