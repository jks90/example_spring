package com.jkstic.testHw.transformer;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.jkstic.testHw.dto.RecetasDto;
import com.jkstic.testHw.models.Recetas;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecetasMapper {

	RecetasMapper INSTANCE = Mappers.getMapper(RecetasMapper.class);
	
	@Mappings({
		@Mapping(target = "nombre", source = "entity.nombre"),
		@Mapping(target = "descripcion", source = "entity.descripcion")})
	RecetasDto recetasToRecetasDto(Recetas entity);

	@Mappings({ 
		@Mapping(target = "nombre", source = "dto.nombre"),
		@Mapping(target = "descripcion", source = "dto.descripcion")})
	Recetas recetasDtotoRecetas(RecetasDto dto);
	
	List<RecetasDto> listRecetasToListRecetasDto(List<Recetas> entity);
	
	List<Recetas> listRecetasDtotoListRecetas(List<RecetasDto> dto);
}
