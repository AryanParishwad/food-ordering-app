import React from 'react';
import FoodItem from './FoodItem'; // Check this import

const FoodList = ({ foods }) => {
  return (
    <div style={{ display: 'flex', flexWrap: 'wrap' }}>
      {foods.map(food => (
        <FoodItem key={food.id} food={food} />
      ))}
    </div>
  );
};

export default FoodList; // Must be default export