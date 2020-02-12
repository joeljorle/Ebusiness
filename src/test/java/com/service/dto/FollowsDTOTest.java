package com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class FollowsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FollowsDTO.class);
        FollowsDTO followsDTO1 = new FollowsDTO();
        followsDTO1.setId(1L);
        FollowsDTO followsDTO2 = new FollowsDTO();
        assertThat(followsDTO1).isNotEqualTo(followsDTO2);
        followsDTO2.setId(followsDTO1.getId());
        assertThat(followsDTO1).isEqualTo(followsDTO2);
        followsDTO2.setId(2L);
        assertThat(followsDTO1).isNotEqualTo(followsDTO2);
        followsDTO1.setId(null);
        assertThat(followsDTO1).isNotEqualTo(followsDTO2);
    }
}
