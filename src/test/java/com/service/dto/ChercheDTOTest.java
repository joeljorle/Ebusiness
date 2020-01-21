package com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class ChercheDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChercheDTO.class);
        ChercheDTO chercheDTO1 = new ChercheDTO();
        chercheDTO1.setId(1L);
        ChercheDTO chercheDTO2 = new ChercheDTO();
        assertThat(chercheDTO1).isNotEqualTo(chercheDTO2);
        chercheDTO2.setId(chercheDTO1.getId());
        assertThat(chercheDTO1).isEqualTo(chercheDTO2);
        chercheDTO2.setId(2L);
        assertThat(chercheDTO1).isNotEqualTo(chercheDTO2);
        chercheDTO1.setId(null);
        assertThat(chercheDTO1).isNotEqualTo(chercheDTO2);
    }
}
