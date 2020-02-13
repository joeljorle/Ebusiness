package com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class TourcategoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TourcategoryDTO.class);
        TourcategoryDTO tourcategoryDTO1 = new TourcategoryDTO();
        tourcategoryDTO1.setId(1L);
        TourcategoryDTO tourcategoryDTO2 = new TourcategoryDTO();
        assertThat(tourcategoryDTO1).isNotEqualTo(tourcategoryDTO2);
        tourcategoryDTO2.setId(tourcategoryDTO1.getId());
        assertThat(tourcategoryDTO1).isEqualTo(tourcategoryDTO2);
        tourcategoryDTO2.setId(2L);
        assertThat(tourcategoryDTO1).isNotEqualTo(tourcategoryDTO2);
        tourcategoryDTO1.setId(null);
        assertThat(tourcategoryDTO1).isNotEqualTo(tourcategoryDTO2);
    }
}
