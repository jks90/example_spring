package com.jkstic.testHw.transformer;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.jkstic.testHw.dto.DiaDto;
import com.jkstic.testHw.models.Dia;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiaMapper {
	
	DiaMapper INSTANCE = Mappers.getMapper(DiaMapper.class);
	
	@Mappings({
		@Mapping(target = "fecha", source = "entity.fecha", dateFormat="dd-MM-yyyy"),
		@Mapping(target = "comentario", source = "entity.comentario"),
		@Mapping(target = "listadoRecetas", source = "entity.listadoRecetas")})
	DiaDto diaToDiaDto(Dia entity);

	@Mappings({ 
		@Mapping(target = "fecha", source = "dto.fecha", dateFormat="dd-MM-yyyy"),
		@Mapping(target = "comentario", source = "dto.comentario")})
	Dia diaDtotoDia(DiaDto dto);
	
	
	List<DiaDto> listDiaToListDiaDto(List<Dia> entity);
	
	
	List<Dia> listDiaDtotoListDia(List<DiaDto> dto);

}
