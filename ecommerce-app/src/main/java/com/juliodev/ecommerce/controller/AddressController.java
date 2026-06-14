package com.juliodev.ecommerce.controller;

import com.juliodev.ecommerce.model.User;
import com.juliodev.ecommerce.payload.AddressDTO;
import com.juliodev.ecommerce.service.AddressService;
import com.juliodev.ecommerce.util.AuthUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Address API", description = "Address endpoints for save shipping addresses in the e-commerce app")
@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    AuthUtil authUtil;

    @Autowired
    AddressService addressService;

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAllAddresses(){
        List<AddressDTO>addressDTOList = this.addressService.getAddresses();
        return new ResponseEntity<>(addressDTOList, HttpStatus.OK);
    }

    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO>getAddressById(@PathVariable Long addressId){
        AddressDTO addressRetrieved = this.addressService.getAddressById(addressId);
        return new ResponseEntity<AddressDTO>(addressRetrieved,HttpStatus.OK);
    }

    @GetMapping("/users/addresses")
    public ResponseEntity<List<AddressDTO>> getUserAddresses(){
        User user = this.authUtil.loggedInUser();
        List<AddressDTO> addressList = addressService.getUserAddresses(user);
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long addressId, @RequestBody AddressDTO addressDTO){

        AddressDTO addressUpdated = this.addressService.updateAddress(addressId,addressDTO);
        return new ResponseEntity<AddressDTO>(addressUpdated, HttpStatus.OK);
    }

    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO>createAddress(@Valid @RequestBody AddressDTO addressDTO){
        User user = this.authUtil.loggedInUser();
        AddressDTO savedAddress = this.addressService.createAddress(addressDTO,user);
        return new ResponseEntity<AddressDTO>(savedAddress, HttpStatus.CREATED);
    }

    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<String> updateAddress(@PathVariable Long addressId){
        String status = addressService.deleteAddress(addressId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }


}
