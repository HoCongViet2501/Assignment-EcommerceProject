const brands = [
  { name: "Dolce&Gabbana" },
  { name: "Calvin Klein" },
  { name: "Chanel" },
  { name: "Dior" },
  { name: "Giorgio Armani" },
  { name: "Givency" },
  { name: "Gucci" },
  { name: "Hermes" },
  { name: "Lancome" },
  { name: "Versace" },
];

const gender = [
  { name: "Male", label: "для женщин" },
  { name: "Female", label: "для мужчин" },
];

const price = [
  { id: 1, name: "any", array: [] },
  { id: 2, name: "30 - 95 $", array: [30, 95] },
  { id: 3, name: "95 - 300 $", array: [95, 300] },
  { id: 4, name: "300 - 590 $", array: [300, 590] },
  { id: 5, name: "590 - 990+ $", array: [590, 990] },
];
export { brands, gender, price };