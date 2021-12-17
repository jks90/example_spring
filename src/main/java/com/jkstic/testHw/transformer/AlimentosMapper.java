package com.jkstic.testHw.transformer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.jkstic.testHw.dto.AlimentoDto;
import com.jkstic.testHw.models.Alimentos;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlimentosMapper {
	
	AlimentosMapper INSTANCE = Mappers.getMapper(AlimentosMapper.class);
	
	@Mappings({
		@Mapping(target = "nombre", source = "entity.nombre"),
		@Mapping(target = "cantidad", source = "entity.cantidad"),
		@Mapping(target = "medida", source = "entity.medida")})
	AlimentoDto alimentosToAlimentoDto(Alimentos entity);

	@Mappings({ 
		@Mapping(target = "nombre", source = "dto.nombre"),
		@Mapping(target = "cantidad", source = "dto.cantidad"),
		@Mapping(target = "medida", source = "dto.medida")})
	Alimentos alimentoDtotoAlimentos(AlimentoDto dto);
	
}
