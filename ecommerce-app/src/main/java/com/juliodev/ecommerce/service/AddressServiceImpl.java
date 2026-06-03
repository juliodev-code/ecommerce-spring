package com.juliodev.ecommerce.service;

import com.juliodev.ecommerce.exceptions.ResourceNotFoundException;
import com.juliodev.ecommerce.model.Address;
import com.juliodev.ecommerce.model.User;
import com.juliodev.ecommerce.payload.AddressDTO;
import com.juliodev.ecommerce.repositories.AddressRepository;
import com.juliodev.ecommerce.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO, User user) {
        Address address = this.modelMapper.map(addressDTO, Address.class);
        address.setUser(user);

        //adding the new address into user in memory for the response
        List<Address> addressesList = user.getAddresses();
        addressesList.add(address);
        user.setAddresses(addressesList);

        //persist the data
        Address addressSaved = this.addressRepository.save(address);
        return this.modelMapper.map(addressSaved, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(address -> modelMapper.map(address, AddressDTO.class))
                .toList();
    }

    @Override
    public AddressDTO getAddressById(Long addressId) {
        Optional<Address>addressFound = this.addressRepository.findById(addressId);
        if(addressFound.isEmpty()){
            throw new ResourceNotFoundException("Address", "addressId",addressId);
        }
        return this.modelMapper.map(addressFound.get(), AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getUserAddresses(User user) {
        List<Address> addresses = user.getAddresses();
        return addresses.stream()
                .map(address -> modelMapper.map(address, AddressDTO.class))
                .toList();
    }

    @Override
    public AddressDTO updateAddress(Long addressId, AddressDTO addressDTO) {
        Address addressFromDatabase = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", addressId));

        //update address information
        addressFromDatabase.setCity(addressDTO.getCity());
        addressFromDatabase.setPincode(addressDTO.getPincode());
        addressFromDatabase.setState(addressDTO.getState());
        addressFromDatabase.setCountry(addressDTO.getCountry());
        addressFromDatabase.setStreet(addressDTO.getStreet());
        addressFromDatabase.setBuildingName(addressDTO.getBuildingName());
        Address updatedAddress = addressRepository.save(addressFromDatabase);

        //update address to user
        User user = addressFromDatabase.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        user.getAddresses().add(updatedAddress);
        userRepository.save(user);

        return modelMapper.map(updatedAddress,AddressDTO.class);
    }

    @Override
    public String deleteAddress(Long addressId) {
        Optional<Address>addressFound = this.addressRepository.findById(addressId);
        if(addressFound.isEmpty()){
            throw new ResourceNotFoundException("Address", "addressId",addressId);
        }
        //we delete the address in users dependency
        User user = addressFound.get().getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        userRepository.save(user);

        //we remove the address
        addressRepository.delete(addressFound.get());
        return "Address deleted successfully with addressId: " + addressId;
    }
}
