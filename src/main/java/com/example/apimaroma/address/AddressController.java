package com.example.apimaroma.address;

import com.example.apimaroma.colors.ColorBean;
import com.example.apimaroma.user.UserBean;
import com.example.apimaroma.utils.wrappers.AddressWrapper;
import com.google.cloud.Timestamp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path="api/v1/user")
@Api(value = "AddressController Resource", description = "Endpoint of Address routes")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @ApiOperation("Adds an address to a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request")
    })
    @PostMapping("/address")
    public AddressBean addAddress(@RequestBody AddressWrapper addressWrapper) throws ExecutionException, InterruptedException {
        return addressService.addAddress((String) addressWrapper.getUser().getId(), addressWrapper.getAddresses().get(0));
    }

    @ApiOperation("Delete a specific address of an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request")
    })
    @DeleteMapping("/address")
    public Timestamp removeAddress(@RequestBody Map<String, Object> bodyMap) throws ExecutionException, InterruptedException {
        return addressService.removeAddress((String) bodyMap.get("userId"),     new AddressBean((String) bodyMap.get("addressId")));
    }

    @ApiOperation("Returns a specific address of an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request")
    })
    @GetMapping("/{userId}/address/{addressId}")
    public AddressBean getAddress(@PathVariable("userId") String userId, @PathVariable("addressId") String addressId) throws ExecutionException, InterruptedException {
        return addressService.getAddress(userId, new AddressBean(addressId));
    }

    @ApiOperation("Returns all addresses of an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request")
    })
    @GetMapping("/{userId}/addresses")
    public List<AddressBean> getAddresses(@PathVariable("userId") String userId) throws ExecutionException, InterruptedException {
        return addressService.getAddresses(userId);
    }



}
