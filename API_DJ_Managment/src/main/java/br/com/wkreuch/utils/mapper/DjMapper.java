package br.com.wkreuch.utils.mapper;

import br.com.wkreuch.data.dtos.AddressResponseDto;
import br.com.wkreuch.data.dtos.DJCreateDto;
import br.com.wkreuch.data.dtos.DJResponseDto;
import br.com.wkreuch.data.vos.AddressVo;
import br.com.wkreuch.models.DJ;

import java.util.List;

public class DjMapper extends BeanUtilsMapper {

    public static DJResponseDto convert(DJ dj, boolean hateoas) {
        DJResponseDto djResponseDto = new DJResponseDto();
        copyProperties(dj, djResponseDto);
        if (dj.getAddress() != null) {
            djResponseDto.setAddress(new AddressResponseDto());
            copyProperties(dj.getAddress(), djResponseDto.getAddress());
        }
        if (hateoas) djResponseDto.addHateosLink();

        return djResponseDto;
    }
    public static DJ convert(DJCreateDto djCreateDto) {
        DJ dj = new DJ();
        copyProperties(djCreateDto, dj);
        if (djCreateDto.getAddress() != null) {
            dj.setAddress(new AddressVo());
            copyProperties(djCreateDto.getAddress(), dj.getAddress());
        }
        return dj;
    }
    public static List<DJResponseDto> convert(List<DJ> djs, boolean hateoas) {
        return djs.stream().map(dj -> convert(dj, hateoas)).toList();
    }

}
