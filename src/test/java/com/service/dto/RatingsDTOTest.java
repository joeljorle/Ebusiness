package com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class RatingsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RatingsDTO.class);
        RatingsDTO ratingsDTO1 = new RatingsDTO();
        ratingsDTO1.setId(1L);
        RatingsDTO ratingsDTO2 = new RatingsDTO();
        assertThat(ratingsDTO1).isNotEqualTo(ratingsDTO2);
        ratingsDTO2.setId(ratingsDTO1.getId());
        assertThat(ratingsDTO1).isEqualTo(ratingsDTO2);
        ratingsDTO2.setId(2L);
        assertThat(ratingsDTO1).isNotEqualTo(ratingsDTO2);
        ratingsDTO1.setId(null);
        assertThat(ratingsDTO1).isNotEqualTo(ratingsDTO2);
    }
}
