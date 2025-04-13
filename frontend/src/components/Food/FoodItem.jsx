import React from 'react';
import { Card, CardContent, Typography } from '@mui/material';

const FoodItem = ({ food }) => {
  return (
    <Card style={{ width: 300, margin: 10 }}>
      <CardContent>
        <Typography variant="h5">{food.name}</Typography>
        <Typography>${food.price}</Typography>
      </CardContent>
    </Card>
  );
};

export default FoodItem; // Must be default export