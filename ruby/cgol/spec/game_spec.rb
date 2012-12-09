require 'game'

describe "Conway's Game of Life" do
  it "Any live cell with fewer than two live neighbours dies, as if caused by under-population."
  it "Any live cell with two or three live neighbours lives on to the next generation."
  it "Any live cell with more than three live neighbours dies, as if by overcrowding."
  it "Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction."

  context Cell do
    describe "a living cell" do
      subject { Cell.new 1 }

      it { should be_alive }
      it { should_not be_dead }
    end

    describe "a dead cell" do
      subject { Cell.new 0 }

      it { should be_dead }
      it { should_not be_alive }
    end

    describe "#next!" do
      before { @cell = Cell.new 1 }

      it "kills a cell if it has less than 2 neighbours" do
        @cell.neighbours = 1
        @cell.next!
        @cell.should be_dead
      end

      it "kills a cell if it has more than 3 neighbors" do
        @cell.neighbours = 4
        @cell.next!
        @cell.should be_dead
      end

      it "resurrects a cell if it has exactly 3 neighbors" do
        @cell.die!
        @cell.neighbours = 3
        @cell.next!
        @cell.should be_alive
      end
    end
  end

  describe Board do
    context ".new" do
      it "sets the number of rows" do
        board = Board.new( 10 )
        board.rows.should equal 10
      end

      it "sets the number of columns equal to the number of rows" do
        board = Board.new( 15 )
        board.columns.should equal 15
      end

      it "fills each grid point with a Cell" do
        board = Board.new( 3 )
        total = board.current.inject( 0 ) do |count, row|
          count += row.inject( 0 ) do |c, cell|
            c += cell.instance_of?( Cell ) ? 1 : 0
            c
          end
          count
        end

        total.should equal 9
      end

      it "sets the probability of a new Cell being alive" do
        Cell.should_receive( :new ).with( 2 ).exactly( 100 ).times
        board = Board.new( 10, 2 )
      end
    end

    context "#neighbors" do
      it "takes a coordinate and returns the neighboring cells" do
        board = Board.new( 5 )
        neighbors = board.neighbors( 3, 3 )
        neighbors.size.should equal 8
      end
    end

    context "#count_living" do
      it "counts the number of cells that are living" do
        board = Board.new
        board.count_living([ Cell.new(1), Cell.new(1), Cell.new(0) ]).should equal 2
      end
    end
  end
end
