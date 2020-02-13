package com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class FollowsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Follows.class);
        Follows follows1 = new Follows();
        follows1.setId(1L);
        Follows follows2 = new Follows();
        follows2.setId(follows1.getId());
        assertThat(follows1).isEqualTo(follows2);
        follows2.setId(2L);
        assertThat(follows1).isNotEqualTo(follows2);
        follows1.setId(null);
        assertThat(follows1).isNotEqualTo(follows2);
    }
}
