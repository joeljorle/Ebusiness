package com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class TourDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TourDTO.class);
        TourDTO tourDTO1 = new TourDTO();
        tourDTO1.setId(1L);
        TourDTO tourDTO2 = new TourDTO();
        assertThat(tourDTO1).isNotEqualTo(tourDTO2);
        tourDTO2.setId(tourDTO1.getId());
        assertThat(tourDTO1).isEqualTo(tourDTO2);
        tourDTO2.setId(2L);
        assertThat(tourDTO1).isNotEqualTo(tourDTO2);
        tourDTO1.setId(null);
        assertThat(tourDTO1).isNotEqualTo(tourDTO2);
    }
}
