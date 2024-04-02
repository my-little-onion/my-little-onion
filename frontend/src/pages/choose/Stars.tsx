interface StarsProps {
  level: number;
}

const Stars = ({ level }: StarsProps) => {
  const blankStars = 3 - level;

  return (
    <div>
      {[...Array(level)].map((_) => (
        <img src='/images/icons/full-star.png' width={70} height={70} />
      ))}
      {[...Array(blankStars)].map((_) => (
        <img src='/images/icons/empty-star.png' width={70} height={70} />
      ))}
    </div>
  );
};

export default Stars;
