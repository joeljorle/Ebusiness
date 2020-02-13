package com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class TourgroupDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TourgroupDTO.class);
        TourgroupDTO tourgroupDTO1 = new TourgroupDTO();
        tourgroupDTO1.setId(1L);
        TourgroupDTO tourgroupDTO2 = new TourgroupDTO();
        assertThat(tourgroupDTO1).isNotEqualTo(tourgroupDTO2);
        tourgroupDTO2.setId(tourgroupDTO1.getId());
        assertThat(tourgroupDTO1).isEqualTo(tourgroupDTO2);
        tourgroupDTO2.setId(2L);
        assertThat(tourgroupDTO1).isNotEqualTo(tourgroupDTO2);
        tourgroupDTO1.setId(null);
        assertThat(tourgroupDTO1).isNotEqualTo(tourgroupDTO2);
    }
}
