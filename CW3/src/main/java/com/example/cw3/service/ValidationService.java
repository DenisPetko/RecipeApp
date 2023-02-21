package com.example.cw3.service;

import com.example.cw3.model.Color;
import com.example.cw3.model.Size;
import com.example.cw3.model.SocksBatch;

public interface ValidationService {
    boolean validate(SocksBatch socksBatch);

    boolean validate(Color color, Size size, int cottonMin, int cottonMax);
}
