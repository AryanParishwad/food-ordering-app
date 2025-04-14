import React, { useEffect, useState } from 'react';
import API from '../services/api';
import FoodList from '../components/Food/FoodList'; // Fix this import

const Menu = () => {
  const [foods, setFoods] = useState([]);

  useEffect(() => {
    const fetchFoods = async () => {
      try {
        const { data } = await API.get('/food');
        setFoods(data);
      } catch (error) {
        console.error('Failed to fetch foods:', error);
      }
    };
    fetchFoods();
  }, []);

  return (
    <div>
      <h1>Our Menu</h1>
      <FoodList foods={foods} />
    </div>
  );
};

export default Menu; // Must have this line
