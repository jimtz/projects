import './App.css';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ShopDish from './components/ShopDish';
import styled from "styled-components"

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`

const DishWrapper = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  width: 500px;
`

const DishName = styled.h4`
  font-size: 16px;
  color: black;
  padding-right: 50px;
`

const ButtonsWrapper = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
`

const ButtonAdd = styled.button`
  color: #fff;
  background-color: #337ab7;
  margin-right: 10px;
  height: 35px;
  width: 100px;
  border: none;
  border-radius: 15px;
  font-size: 24px;
  font-weight: 500;
  transition: all 0.3s ease;
  cursor: pointer;
  &:hover{
    opacity: 0.7;
  }
`

const ButtoonRemove = styled.button`
  color: #fff;
  background-color: #d9534f;
  height: 35px;
  width: 100px;
  border: none;
  border-radius: 15px;
  font-size: 24px;
  font-weight: 500;
  transition: all 0.3s ease;
  cursor: pointer;
  margin-right: 25px;
  &:hover{
    opacity: 0.7;
  }
`

const DishQuantity = styled.h4`
  font-size: 16px;
  color: black;
`

function App() {
  const [dishes, setDishes] = useState([]);
  const [numberOfDishes, setNumberOfDishes] = useState(Array(11).fill(0));
  const [cost, setCost] = useState(0);
  useEffect(() => {
    axios.get("http://localhost:8080/user/home").then((res) => {
      console.log(res.data)
      setDishes(res.data)
    })
  },[])

  const computeCost = (cost, index) => {
    setCost((prev) => prev + cost);
  }

  const removeCost = (cost, index) => {
    setCost((prev) => prev - cost)
  }

  const displayTotalCost = () => {
    if(cost > 0){
      return cost > 100 ? cost*0.9 : cost
    }else{
      return 0;
    }
  }

  return (
    <div className="App">
        <div>
          <div>ShopDish</div>
          <h1>Welcome to Jim's Dish Shop!  </h1>
          <i>Please, select the dishes you 're considering to buy</i>
        </div>
        <Wrapper>
          {(dishes || []).map((d, index) => (
            <DishWrapper>
              <DishName>{d._name}</DishName>
              <DishName>{d._cost}</DishName>
              <ButtonAdd onClick={() => computeCost(d._cost, index)}>+</ButtonAdd>
              <ButtoonRemove onClick={() => removeCost(d._cost, index)}>-</ButtoonRemove>
            </DishWrapper>
        ))}
        </Wrapper>

        <h2>Total Cost: {cost < 0 ? 0 : cost} Total Cost with Discount: {displayTotalCost()}</h2>
        

    </div>
  );
}

export default App;
