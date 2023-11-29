package br.com.wkreuch.models.mock;

import br.com.wkreuch.data.dtos.AddressCreateDto;
import br.com.wkreuch.data.dtos.DJCreateDto;
import br.com.wkreuch.data.vos.AddressVo;
import br.com.wkreuch.models.DJ;

import java.time.LocalDate;

public class DJMock {

    public DJ mockDJ(Long id) {
        DJ dj = new DJ();

        dj.setIdDj(id);
        dj.setFirstName("Pierre David " + id);
        dj.setLastName("Guetta " + id);
        dj.setBirthDate(LocalDate.parse("1967-10-07"));
        dj.setArtistName("David Guetta");
        dj.setCountryIdRegistration("123456");

        var address = new AddressVo();
        address.setStreet("Elliot Courts " + id);
        address.setNumber(634L);
        address.setComplement("Apt. 492");
        address.setDistrict("Central");
        address.setCity("Kameronmouth");
        address.setState("South Carolina");
        address.setCountry("United States");
        address.setPostalCode("997720868");
        dj.setAddress(address);

        return dj;
    }

    public DJCreateDto mockDJCreateDto(Long id) {
        DJCreateDto dj = new DJCreateDto();

        dj.setFirstName("Pierre David " + id);
        dj.setLastName("Guetta " + id);
        dj.setBirthDate(LocalDate.parse("1967-10-07"));
        dj.setArtistName("David Guetta");
        dj.setCountryIdRegistration("123456");

        var address = new AddressCreateDto();
        address.setStreet("Elliot Courts " + id);
        address.setNumber(634L);
        address.setComplement("Apt. 492");
        address.setDistrict("Central");
        address.setCity("Kameronmouth");
        address.setState("South Carolina");
        address.setCountry("United States");
        address.setPostalCode("997720868");
        dj.setAddress(address);

        return dj;
    }


}
