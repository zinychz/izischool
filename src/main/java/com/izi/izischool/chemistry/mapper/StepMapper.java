package com.izi.izischool.chemistry.mapper;

import com.izi.izischool.chemistry.dto.StepRequest;
import com.izi.izischool.chemistry.model.step.Step;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StepMapper {
    Step toStep(StepRequest stepRequest);
}
