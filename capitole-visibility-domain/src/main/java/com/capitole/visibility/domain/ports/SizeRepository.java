package com.capitole.visibility.domain.ports;

import com.capitole.visibility.domain.agregate.SizeBasicInformation;

import java.util.List;

public interface SizeRepository {

    List<SizeBasicInformation> findAll();
}
