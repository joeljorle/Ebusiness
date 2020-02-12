package com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class ActusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActusDTO.class);
        ActusDTO actusDTO1 = new ActusDTO();
        actusDTO1.setId(1L);
        ActusDTO actusDTO2 = new ActusDTO();
        assertThat(actusDTO1).isNotEqualTo(actusDTO2);
        actusDTO2.setId(actusDTO1.getId());
        assertThat(actusDTO1).isEqualTo(actusDTO2);
        actusDTO2.setId(2L);
        assertThat(actusDTO1).isNotEqualTo(actusDTO2);
        actusDTO1.setId(null);
        assertThat(actusDTO1).isNotEqualTo(actusDTO2);
    }
}
