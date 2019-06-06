/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplecalculator;

/**
 *
 * @author freez
 */
enum Operation{
        ADD{
            @Override
            public double math(double _one, double _two){
                return _one + _two; 
            }
        },
        SUB{
            @Override
            public double math(double _one, double _two){
                return _one - _two; 
            }
        },
        MUL{
            @Override
            public double math(double _one, double _two){
                return _one * _two; 
            }
        },
        DIV{
            @Override
            public double math(double _one, double _two){
                return _one / _two; 
            }
        };
        
        public abstract double math(double _one, double _two); 
    }