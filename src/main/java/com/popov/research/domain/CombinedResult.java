package com.popov.research.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
public class CombinedResult {

    public String welcome;

    public User user;

    public Department department;

    public Achievement achievement;
}
